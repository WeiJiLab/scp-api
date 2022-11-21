package com.thoughtworks.ssr.infrastructure.aws.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//TODO 可以放到util或者
@Slf4j
public class OperateObject {
    private static final String regionName = "ap-northeast-1";

    public static PutObjectResult putObject(String filePath, String bucketName) throws AmazonServiceException {
        System.out.format("Uploading %s to S3 bucket %s...\n", filePath, bucketName);
        String keyName = Paths.get(filePath).getFileName().toString();
        System.out.format("Uploading %s to S3 bucket %s...\n", filePath, bucketName);
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(regionName).build();
        try {
            return s3.putObject(bucketName, keyName, new File(filePath));
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            log.info("upload to s3 failed");
            throw e;
        }
    }

    public static List<S3ObjectSummary> listObject(String bucketName) {
        System.out.format("Objects in S3 bucket %s:\n", bucketName);
//        AWSCredentials credentials = new BasicAWSCredentials("","");

        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(regionName)
//                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
        ListObjectsV2Result result = s3.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        for (S3ObjectSummary os : objects) {
            System.out.println("* " + os.getKey());
        }
        return objects;
    }

    public static String downloadObject(String bucketName, String keyName, String useCasePath) {
        System.out.format("Downloading %s from S3 bucket %s...\n", keyName, bucketName);
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(regionName).build();
        try {
            S3Object o = s3.getObject(bucketName, keyName);
            S3ObjectInputStream s3is = o.getObjectContent();
            File filePath = new File(useCasePath);
            filePath.mkdirs();
            File file = new File(useCasePath + "/" + keyName);
            FileOutputStream fos = new FileOutputStream(file);
            byte[] readBuf = new byte[1024];
            int readLen = 0;
            while ((readLen = s3is.read(readBuf)) > 0) {
                fos.write(readBuf, 0, readLen);
            }
            s3is.close();
            fos.close();
            return useCasePath + "/" + keyName;
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }

    public static void deleteObjects(String bucketName, List<String> keyNameList) {
        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(regionName)
                    .build();
            ArrayList<DeleteObjectsRequest.KeyVersion> keys = new ArrayList<DeleteObjectsRequest.KeyVersion>();
            for (String tmp : keyNameList) {
                keys.add(new DeleteObjectsRequest.KeyVersion(tmp));
            }

            // Delete the sample objects.
            DeleteObjectsRequest multiObjectDeleteRequest = new DeleteObjectsRequest(bucketName)
                    .withKeys(keys)
                    .withQuiet(false);

            // Verify that the objects were deleted successfully.
            DeleteObjectsResult delObjRes = s3Client.deleteObjects(multiObjectDeleteRequest);
            int successfulDeletes = delObjRes.getDeletedObjects().size();
            System.out.println(successfulDeletes + " objects successfully deleted.");
        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }
    }
}
