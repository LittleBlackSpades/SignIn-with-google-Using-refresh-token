import Ember from 'ember';

export default Ember.Controller.extend({
    actions:{
        signIn: function(){
            var oauth2Endpoint = 'https://accounts.google.com/o/oauth2/v2/auth';
  
            var form = document.createElement('form');
            form.setAttribute('method', 'GET'); 
            form.setAttribute('action', oauth2Endpoint);
          
            var params = {'client_id': '986853611746-2f92eqbkr7p35o9nr8uk885gq69b1dvk.apps.googleusercontent.com',
                          'redirect_uri': 'http://localhost:4200/home',
                          'access_type' : 'offline',
                          'response_type': 'code',
                          'scope': 'https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email',
                          'state': 'pass-through value'};
          
            for (var p in params) {
              var input = document.createElement('input');
              input.setAttribute('type', 'hidden');
              input.setAttribute('name', p);
              input.setAttribute('value', params[p]);
              form.appendChild(input);
            }
            document.body.appendChild(form);
            form.submit();
        }
    }
});
