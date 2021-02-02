resource "aws_s3_bucket" "inspec-report-bucket" {
  bucket = "sss-inspec-report-bucket-ap-northeast-1"
  acl    = "private"
  region = "ap-northeast-1"
  versioning {
    enabled = false
  }
}
