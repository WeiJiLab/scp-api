# Bootstrap backend for terraform

## Quick Start

We use terraform to create a s3 bucket for terraform state storage

Run once to set the credential of securityFederatedAdmin
```
aws configure --profile securityFederatedAdmin 
```

```
terraform init
terraform plan
terraform apply
```
