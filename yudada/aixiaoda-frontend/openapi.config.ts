const { generateService } = require("@umijs/openapi");

generateService({
  //你请求的request文件路径：request.ts
  requestLibPath: "import request from '@/request'",
  //根据哪个地址的接口文档进行生成
  schemaPath: "http://localhost:8101/api/v2/api-docs",
  //你要在哪个页面下生成
  serversPath: "./src",
});
