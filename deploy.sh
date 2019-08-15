date=`date +%Y%m%d`
dateFormatted=`date -R`
s3Bucket="ARTIFACTS_BUCKET"
fileName="FILE_NAME"
relativePath="/${s3Bucket}/${fileName}"
contentType="application/octet-stream"
stringToSign="PUT\n\n${contentType}\n${dateFormatted}\n${relativePath}"
s3AccessKey="ARTIFACTS_KEY"
s3SecretKey="ARTIFACTS_SECRET"
signature=`echo -en ${stringToSign} | openssl sha1 -hmac ${s3SecretKey} -binary | base64`
curl -X PUT -T "${fileName}" \
-H "Host: ${s3Bucket}.s3.amazonaws.com" \
-H "Date: ${dateFormatted}" \
-H "Content-Type: ${contentType}" \
-H "Authorization: AWS ${s3AccessKey}:${signature}" \

http://${s3Bucket}.s3.amazonaws.com/${fileName}