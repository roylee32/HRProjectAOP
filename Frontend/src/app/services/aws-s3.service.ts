import { Injectable } from '@angular/core';
// import * as AWS from 'aws-sdk/global';
// import * as S3 from 'aws-sdk/clients/s3';

@Injectable({
  providedIn: 'root'
})
export class AWSS3Service {

  // bucket = new S3({
  //   accessKeyId: 'AKIA2TFHIUXM5QDPVOUO',
  //   secretAccessKey: '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',
  //   region:'us-east-2'
  // });

  constructor() { }

  // getDefaultAvatar(){
  //   const params = {
  //     Bucket: 'beaconfiretp1',
  //     Key: 'default_avatar.png'
  //   }
  //   var url = this.bucket.getSignedUrl('getObject', params);
  //   return url;
  // }


}
