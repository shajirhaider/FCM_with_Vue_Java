<template>
  <div id="">
      <p>
        token:
        <br>
        <b>{{this.token}}</b> 
      </p>
      <hr>
      <p>
        score: <b>{{message.score}}</b>
        <br>
        time : <b>{{message.time}}</b>       
      </p>
       
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
        const messaging = firebase.messaging();
        messaging
        .getToken()
        .then(currentToken => {
            console.log(currentToken);
            this.token = currentToken
            console.log("token", this.token)
            return
        })
        .catch(function (err) {
            console.log("An error occurred while retrieving token. ", err);
        });

        messaging.onMessage(payload =>  {
            this.message = payload.data
            console.log("message", this.message)
            console.log(payload);
        });
    },
    destroyed(){
        this.token = "";
        console.log("distroyed token",this.token)

    }

    

}
</script>