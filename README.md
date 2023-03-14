# Chat2QQ
这是个人出于兴趣开发的使用ChatGPT API的项目，仅仅满足了bot接入群可以接受指令进行chat的需求。
使用了Mirai core、Okhttp3作为主要的第三方开源依赖，详见MVN的pom文件。

## 功能支持
通过群聊消息指定打开chat功能，并支持上下文。

## 使用方法
clone该仓库，在自己的IDE中打开Main类进行编译打包。

请注意本项目将用到几个环境变量，请确定可以通过CLI echo $params获取其值。
* OPENAI_SK   【你的OpenAI SK】
* MIRAI_QQ  【你的Bot QQ号】
* MIRAI_PASS  【你的Bot QQ密码】
* MIRAI_OWNER 【你的Bot Owner（一般是你自己）的QQ号】

当看到控制台中提示绿色字体的 **"Bot login successful"** 时，Bot Owner可以对有Bot存在的QQ群中输入<br/>`/enablechat num cmd`进行聊天功能激活，
之后可以直接at 机器人进行交互。

以上指令中num表示本次chat支持的最大上下文长度，cmd则是本次chat的启动指令，用于提示bot的回答方向。<br/>
详见<https://github.com/f/awesome-chatgpt-prompts>


## 项目结构

这个项目结构如下

```.
├── java
│   └── Moonold
│       ├── client(Mirai bot逻辑、Http包的封装）
│       └── entity（各种数据模型）
│           └── chat
│               ├── request
│               └── response
└── resources
```

## Todo List
* 支持各群和不同成员的数据隔离
* 提供打包release
* 提供缓存、高并发支持
* 迁移到Spring中