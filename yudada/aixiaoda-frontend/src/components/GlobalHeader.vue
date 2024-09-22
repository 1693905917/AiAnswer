<template>
  <!--  :wrap="false" ：不让导航栏随着页面缩小而换行-->
  <a-row id="globalHeader" align="center" :wrap="false">
    <!--flex="auto"：自动占位  相当于别人不敢占的，我来填充-->
    <a-col flex="auto">
      <a-menu
          mode="horizontal"
          :selected-keys="selectedKeys"
          @menu-item-click="doMenuClick"
      >
        <a-menu-item
            key="0"
            :style="{ padding: 0, marginRight: '38px' }"
            disabled
        >
          <!--存放项目标志-->
          <div class="titleBar">
            <!--TODO 1.修改图标-->
            <!--assets文件是内部项目css等文件访问的，public文件是给用户公开可访问到的  -->
            <img class="logo" src="../assets/logo.png"/>
            <div class="title">AI小答</div>
          </div>
        </a-menu-item>
        <a-menu-item v-for="item in visibleRoutes" :key="item.path">
          {{ item.name }}
        </a-menu-item>
      </a-menu>
    </a-col>
    <a-col flex="200px">
      <a-space>
        <div v-if="loginUserStore.loginUser.id">
          {{ loginUserStore.loginUser.userName ?? "匿名用户" }}
          <Avatar>
            <img :src="loginUserStore.loginUser.userAvatar" alt="avatar"/>
          </Avatar>
          <a-popconfirm
              content="是否登出"
              position="bottom"
              type="warning"
              @ok="logout"
          >
            <a-button class="button">登出</a-button>
          </a-popconfirm>
        </div>
        <div v-else>
          <a-button href="/user/login" type="primary">登录</a-button>
        </div>
      </a-space>
    </a-col>
    <!--    TODO 第一次修改-->
    <!--    <a-col flex="100px">-->
    <!--      <div v-if="loginUserStore.loginUser.id">-->
    <!--        {{ loginUserStore.loginUser.userName ?? "匿名用户" }}-->
    <!--      </div>-->
    <!--      <div v-else>-->
    <!--        <a-button type="outline" href="/user/login" shape="round"> 登录</a-button>-->
    <!--      </div>-->
    <!--    </a-col>-->
  </a-row>
</template>

<script setup lang="ts">
//@表示从src目录下引入
import {routes} from "@/router/routes";
import {useRouter} from "vue-router";
import {computed, ref} from "vue";
import {useLoginUserStore} from "@/store/userStore"
import message from "@arco-design/web-vue/es/message";
import { Avatar } from "@arco-design/web-vue";
import checkAccess from "@/access/checkAccess";
import {userLogoutUsingPost} from "@/api/userController";

const loginUserStore = useLoginUserStore();
//useRouter:控制页面的跳转
const router = useRouter();
// 当前选中的菜单项
const selectedKeys = ref(["/"]);
// 路由跳转之后，自动更新选中的菜单项   afterEach方法是路由跳转之后自动执行的  to:跳转到的路由  from：从哪个路由跳转的  failure：失败
router.afterEach((to, from, failure) => {
  //将高亮显示在当前选中的菜单项
  selectedKeys.value = [to.path];
});

// 展示在菜单栏的路由数组
const visibleRoutes = computed(() => {
  //将routes中的路由过滤一遍，根据meta中hideInMenu的值进行过滤
  return routes.filter((item) => {
    //item.meta?加个？代表 meta后面的hideInMenu可有可没有
    if (item.meta?.hideInMenu) {
      return false;
    }
    // 根据权限过滤菜单
    if (!checkAccess(loginUserStore.loginUser, item.meta?.access as string)) {
      return false;
    }
    return true;
  });
});

// 点击菜单跳转到对应页面  key：当前选中的菜单项 而这个key读取的就是<a-menu-item v-for="item in visibleRoutes" :key="item.path">中的:key的值
const doMenuClick = (key: string) => {
  router.push({
    path: key,
  });
};
//<!--    TODO 第二次修改-->
const logout = async () => {
  const res = await userLogoutUsingPost();
  if (res.data.code === 0) {
    message.success("退出成功");
  } else {
    message.error("退出失败，" + res.data.message);
  }
  //刷新页面的函数
  window.location.reload();
};
</script>

<style scoped>
#globalHeader {
}

.titleBar {
  display: flex;
  align-items: center;
}

.title {
  margin-left: 16px;
  color: black;
  font-weight: 600;
  font-size: 20px;
}

.logo {
  height: 48px;
}
</style>
