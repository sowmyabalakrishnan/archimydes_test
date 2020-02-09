import React, { Component } from "react";
import { Button, FormGroup, Input, Label, Container} from "reactstrap";
import "./Login.css";
import { withCookies } from 'react-cookie';

class Login extends Component {
	
	emptyItem = {
    name: '',
    password: '',
	roleId:''
  };


  state = {
    isAuthenticated: false,
    user: undefined,
	item:this.emptyItem,	
	errorMsg:''
  };


constructor(props) {
    super(props);
    const {cookies} = props;
    this.state.csrfToken = cookies.get('XSRF-TOKEN');
this.handleChange = this.handleChange.bind(this);
this.handleSubmit = this.handleSubmit.bind(this);
this.validateForm = this.validateForm.bind(this);
}

async componentDidMount() {
  }

handleChange(event) {
    const target = event.target;
    //const value = target.value;
	const value = target.type === 'checkbox' ? target.checked : target.value;
    const name = target.name;
    let item = {...this.state.item};
    item[name] = value;
    this.setState({item});
  }

  validateForm() {
	console.log("validateForm");
	//return true;
    return this.state.item.name.length > 0 && this.state.item.password.length > 0;
  }

 async handleSubmit(event) {
    event.preventDefault();
	console.log("Logged In");
    const {item} = this.state;
	console.log(this.state.item.roleId);
	if(this.validateForm)
	{
		let link = '';
	link=(this.state.item.roleId && this.state.item.roleId == true)?'/api/admin-login' : '/api/login';
	console.log(link);
	

 	const response =  await fetch(link, {method: 'POST',headers:{'Accept': 'application/json','Content-Type': 'application/json'}, body: JSON.stringify(item), credentials: 'include'});
	const body = await response.text();
    if (body === '') {
      this.setState({isAuthenticated: false, errorMsg: 'Invalid credentials. '});
    } else {
      this.setState({isAuthenticated: true, user: JSON.parse(body)});
		//localStorage.setItem('loggedIn', true);
    }
	console.log("isAuthenticated -- "+this.state.isAuthenticated);
	console.log("isAuthenticated -- "+this.state.errorMsg);
	console.log("user -- "+this.state.user);	
	}
	else
	{
		 this.setState({errorMsg: 'Invalid credentials. '});
	}
	this.props.history.replace({ pathname: '/', state: this.state });
 	this.props.history.push('/');
	
  }

  render(){
	return (	
	 <div className="Login">
      <form onSubmit={this.handleSubmit}>
        <FormGroup>
		 <Label for="name">User Name</Label>
         <Input type="text" name="name" id="name" value={this.state.item.name}  onChange={this.handleChange}/>
        </FormGroup>
   		<FormGroup>
 		<Label for="password">Password</Label>
        <Input type="text" name="password" id="password" value={this.state.item.password}  onChange={this.handleChange}/>
		</FormGroup>
		<div class="form-check" >
           <input type="checkbox" name="roleId" id="roleId" checked={this.state.item.roleId} onChange={this.handleChange}/>
           <label for="roleId">Admin</label>
        </div>
       <Button type="submit" onClick={this.handleSubmit}>
          Login
       </Button>
</form>
</div>

);
}	
}
export default withCookies(Login);