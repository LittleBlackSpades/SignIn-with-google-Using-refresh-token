import Ember from 'ember';
import $ from 'jquery';
export default Ember.Route.extend({

   model() {
        const currentURL = window.location.href;
        const urlSearchParams = new URLSearchParams(currentURL);
        const code = urlSearchParams.get('code');
        var params = {
        'grant_type': 'authorization_code',
        'code': code,
        'client_id': '986853611746-2f92eqbkr7p35o9nr8uk885gq69b1dvk.apps.googleusercontent.com',
        'client_secret': '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',
        'redirect_uri': 'http://localhost:4200/home'
      };
      $.ajax({
          url:"https://accounts.google.com/o/oauth2/token",
          type:"POST",
          data:params,
          async:false,
  
          success:function(response)
          {
                let data = {
                  access_token: response.access_token,
                  refresh_token: response.refresh_token,
                  expires_in: response.expires_in < Date.now() / 1000
                }
                $.ajax({
                    url:"http://localhost:8080/OauthSignIn/token",
                    type:"POST",
                    data:JSON.stringify(data),
                    async:false,
            
                    success:function(response)
                    {
                      alert("Successfully logged in");
            
                    },
                    error:function(response)
                    {
                      alert(response.status);
                    }

                });
          },

          error:function(response)
          {
            alert(response.responseJSON);
          }
        });
         



        //const accessToken = urlSearchParams.get('access_token');
        // debugger;
        // console.log(accessToken);
        // $.ajax({
        //   url:"http://localhost:8080/OauthSignIn/token",
        //   type:"POST",
        //   data:JSON.stringify(accessToken),
        //   async:false,
  
        //   success:function(response)
        //   {
        //     alert("Access token is " + response.access_token);
  
        //   },
        //   error:function(response)
        //   {
        //     alert("Cannot pass the access token " + response.length);
        //   }
        // });
  
},
afterModel() {
  console.log(window.location.href);
  let baseUrl = window.location.origin + window.location.pathname;
  window.history.replaceState({}, document.title, baseUrl);
}
});
