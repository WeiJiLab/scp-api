resource "aws_s3_bucket" "inspec-report-bucket" {
  bucket = "sss-inspec-report-bucket-ap-northeast-1"
  acl    = "public-read"
  region = "ap-northeast-1"
  versioning {
    enabled = false
  }
}

resource "aws_s3_bucket_policy" "inspec-report-bucket-policy" {
  bucket = aws_s3_bucket.inspec-report-bucket.id

  policy = <<POLICY
{
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": "*",
      "Action": "s3:*",
      "Resource": "arn:aws:s3:::sss-inspec-report-bucket-ap-northeast-1/*"
    }
  ]
}
POLICY
}
