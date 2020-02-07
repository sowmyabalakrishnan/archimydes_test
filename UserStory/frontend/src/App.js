import React, { Component } from 'react';
import './App.css';
import Login from './Login';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import UserStoryList from './UserStoryList';
import UserStoryEdit from './UserStoryEdit';
import { CookiesProvider } from 'react-cookie';


class App extends Component {
  render() {
    return (
	<CookiesProvider>
      <Router>
        <Switch>
         
		<Route path='/login' exact={true} component={Login}/>
		<Route path='/' exact={true} component={Home}/>
          <Route path='/userstories' exact={true} component={UserStoryList}/>
		  <Route path='/story/:id'  exact={true} component={UserStoryEdit}/>
        </Switch>
      </Router>
	</CookiesProvider>
    )
  }
}

export default App;