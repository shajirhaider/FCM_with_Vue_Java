<template>
  <div id="app">
      <router-link to="/demo" tag="button">
        click to get token
      </router-link>
     <router-view></router-view>
  </div>
</template>

<script>

import firebase from "firebase/app";
import "firebase/messaging";
export default {
    data(){
        return{
            token:"",
            message:""
        }
    },
    mounted(){
        var config ={
            apiKey: "AIzaSyCvPU7KaC1CtDqfE8Y3430ScmD69BTIRks",
            authDomain: "testfcm-90b81.firebaseapp.com",
            databaseURL: "https://testfcm-90b81.firebaseio.com",
            projectId: "testfcm-90b81",
            storageBucket: "testfcm-90b81.appspot.com",
            messagingSenderId: "324344784855"
        };
        firebase.initializeApp(config);

        const messaging = firebase.messaging();
        //Include vapid keys as its recommended for web push
        messaging.usePublicVapidKey('BKENmyJa3BvqPBrI_oNvWpooUqqjIkukaprBNKxsfj3FDOyodAWMIY4WcmKOmLh4A4ENZ3oQ4VAGimBNalEYF-0');
        messaging
        .requestPermission()
        .then(function () {
            console.log("Notification permission granted.");
        })
        .catch(function (err) {
            console.log("Unable to get permission to notify.", err);
        });

        // messaging
        // .getToken()
        // .then(currentToken => {
        //     console.log(currentToken);
        //     this.token = currentToken
        //     console.log("token", this.token)
        //     return
        // })
        // .catch(function (err) {
        //     console.log("An error occurred while retrieving token. ", err);
        // });

        // messaging.onMessage(payload =>  {
        //     this.message = payload.data
        //     console.log("message", this.message)
        // console.log(payload);
        // });
        // }

        }
}
</script>


<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
