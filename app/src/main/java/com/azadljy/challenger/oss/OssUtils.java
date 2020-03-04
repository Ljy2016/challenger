package com.azadljy.challenger.oss;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSAuthCredentialsProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;

/*******************************************************
 * 项目名称：challenger
 * 创建者： Ljy
 * 创建日期： 2020/2/12
 * Email：enjoy_azadljy@sina.com
 * 描述：//todo
 ********************************************************/
public class OssUtils {

    String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";

    String stsServer = "STS应用服务器地址，例如http://abc.com";
    // 推荐使用OSSAuthCredentialsProvider。token过期可以及时更新。
    OSSCredentialProvider credentialProvider = new OSSAuthCredentialsProvider(stsServer);

    OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider);


}
