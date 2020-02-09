import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavBar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';
import { withCookies, Cookies  } from 'react-cookie';
import { instanceOf } from 'prop-types';


class Home extends Component {	
	static propTypes = {
        cookies: instanceOf(Cookies).isRequired
      };

   state = {
    isAuthenticated: false,
    user: undefined,
	errorMsg:'',
	item:[]
  };

constructor(props) {
    super(props);
  }

async componentDidMount() {
	if(this.props.location.state)
	{
	this.setState({isAuthenticated: this.props.location.state.isAuthenticated, errorMsg: this.props.location.state.errorMsg, user: this.props.location.state.user, item: this.props.location.state.item})
	}
  }

  logout() {
    fetch('/api/logout', {method: 'POST', credentials: 'include'}).then(res => res.json())
      .then(response => {
       // window.location.href = response.logoutUrl + "?id_token_hint=" +
        //  response.idToken + "&post_logout_redirect_uri=" + window.location.origin;
window.location.href = window.location.origin;
      });
  }

  render() {
  const message = this.state.user ?
      <h2>Welcome, {this.state.user.name}!</h2> :
      <p>{this.state.errorMsg} Please log in to the application.</p>;

    const button = (this.state.isAuthenticated && this.state.item && this.state.item.roleId==true) ?
      <div>
        <Button color="link"><Link to={{pathname: '/userstories', state: {user: this.state.user, item: this.state.item}}}>User Stories</Link></Button>
        <br/>
        <Button color="link" onClick={this.logout}>Logout</Button>
      </div> :

		((this.state.isAuthenticated)?
	<div>
		<Button color="warning"><Link to={{pathname: '/story/new', state: {user: this.state.user, item: this.state.item}}}>Add UserStories</Link></Button>
		<br/>
        <Button color="link"><Link to={{pathname: '/userstories', state: {user: this.state.user, item: this.state.item}}}>User Stories</Link></Button>
        <br/>
        <Button color="link" onClick={this.logout}>Logout</Button>
      </div> :

      <Button outline color="primary"><Link to="/login">Login</Link></Button>)

    return (
      <div>
		<AppNavbar/>
        <Container fluid>
           {message}
          {button}
        </Container>
      </div>
    );
  }
}

export default withCookies(Home);