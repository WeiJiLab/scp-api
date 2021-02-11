package com.thoughtworks.security.scpapi.infrastructure.aws.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.util.Iterator;

public class DeleteBucket {
    public void deleteBucket(String bucketName) {
        System.out.println("Deleting S3 bucket: " + bucketName);
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
        try {
            System.out.println(" - removing objects from bucket");
            ObjectListing objectListing = s3.listObjects(bucketName);
            while (true) {
                for (Iterator<?> iterator =
                     objectListing.getObjectSummaries().iterator();
                     iterator.hasNext();) {
                    S3ObjectSummary summary = (S3ObjectSummary) iterator.next();
                    s3.deleteObject(bucketName, summary.getKey());
                }

                // more object_listing to retrieve?
                if (objectListing.isTruncated()) {
                    objectListing = s3.listNextBatchOfObjects(objectListing);
                } else {
                    break;
                }
            }

            System.out.println(" - removing versions from bucket");
            VersionListing versionListing = s3.listVersions(
                    new ListVersionsRequest().withBucketName(bucketName));
            while (true) {
                for (Iterator<?> iterator =
                     versionListing.getVersionSummaries().iterator();
                     iterator.hasNext();) {
                    S3VersionSummary vs = (S3VersionSummary) iterator.next();
                    s3.deleteVersion(
                            bucketName, vs.getKey(), vs.getVersionId());
                }

                if (versionListing.isTruncated()) {
                    versionListing = s3.listNextBatchOfVersions(
                            versionListing);
                } else {
                    break;
                }
            }

            System.out.println(" OK, bucket ready to delete!");
            s3.deleteBucket(bucketName);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
        System.out.println("Done!");
    }
}
