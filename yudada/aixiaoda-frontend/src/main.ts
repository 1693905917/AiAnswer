import { createApp } from "vue";
import App from "./App.vue";
import ArcoVue from "@arco-design/web-vue";
import { createPinia } from "pinia";
import "@arco-design/web-vue/dist/arco.css";
import router from "./router";
import "@/access";
import ArcoVueIcon from "@arco-design/web-vue/es/icon";

const pinia = createPinia();
//<!--TODO 修改第五：use(ArcoVueIcon)-->
createApp(App).use(ArcoVue).use(pinia).use(router).use(ArcoVueIcon).mount("#app");
