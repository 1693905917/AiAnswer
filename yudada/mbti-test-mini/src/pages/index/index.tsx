import { Image, View } from "@tarojs/components";
import Taro from "@tarojs/taro";
import { AtButton } from "taro-ui";
import "taro-ui/dist/style/components/button.scss"; // 按需引入
import "./index.scss";
import GlobalFooter from "../../components/GlobalFooter";
import headerBg from "../../assets/headerBg.jpg";


/**
 *  做题页面
 */
export default () => {
  return (
    <View className="indexPage">
      <View className="at-article__h1 title">MBTI 性格测试</View>
      <View className="at-article__h2 subTitle">
        只需 2 分钟，就能非常准确地描述出你是谁，以及你的性格特点
      </View>
      <AtButton
        type="primary"
        circle
        className="enterBtn"
        onClick={() => {
          Taro.navigateTo({
            url: "/pages/doQuestion/index",
          });
        }}
      >
        开始测试
      </AtButton>
      <Image src={headerBg} className="headerBg" />
      <GlobalFooter />
    </View>
  );
};
