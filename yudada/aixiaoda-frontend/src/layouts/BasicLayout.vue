<template>
  <div id="basicLayout">
    <a-layout style="height: 100vh">
      <a-layout-header class="header">
        <GlobalHeader/>
      </a-layout-header>
      <a-layout-content class="content">
        <!--由于我们中间内容是动态替换的，所以使用这个组件router-view 咱们项目的路由库的语法-->
        <router-view/>
      </a-layout-content>
      <a-layout-footer class="footer">
        <!--target="_blank" 在新页面打开 text-decoration: none:取消链接下划线 -->
        <a href="https://github.com/1693905917/AiAnswer.git" target="_blank" style="text-decoration: none">
          AI小答 by 程序员小志
        </a>
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<script setup lang="ts">
import GlobalHeader from "@/components/GlobalHeader.vue";
</script>

<style scoped>
#basicLayout {
}

#basicLayout .header {
  margin-bottom: 16px;
  box-shadow: #eee 1px 1px 5px;
}

#basicLayout .content {
  max-width: 1200px;
  width: 100%;
  box-sizing: border-box;
  margin: 0 auto 28px;
  padding: 20px;
  background: linear-gradient(to right, #fefefe, #fff);
}

.footer {
  padding: 16px;
  text-align: center;
  background: #efefef;
}
</style>
