import React, { Component } from 'react';
import UserService from '../../services/user.service';
import User from '../../models/user';
 class LoginPage extends Component {
     constructor(props)
     {
         super(props);
         if(UserService.currentUserValue){
             this.props.history.push('/');
         }
     }
    render() {
        return (
            <div>
                
            </div>
        )
    }
}

export default LoginPage;