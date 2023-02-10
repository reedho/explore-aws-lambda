#!/usr/bin/env bash
mybucketname=aws-sam-cli-managed-default-samclisourcebucket-5egkxq3archm
objects=$(aws s3api list-objects-v2 --bucket $mybucketname --query Contents | jq -r 'sort_by(.LastModified) | map(.Key) | .[0:length-1] | .[]')
echo $objects
for row in $objects; do
    aws s3 rm s3://${mybucketname}/${row}
done
