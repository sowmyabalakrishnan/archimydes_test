import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import UserStoryList from './UserStoryList';
import UserStoryEdit from './UserStoryEdit';


class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          <Route path='/userstories' exact={true} component={UserStoryList}/>
		  <Route path='/userstory/:id'  exact={true} component={UserStoryEdit}/>
		  <Route path='/userstory/' exact={true} component={UserStoryEdit}/>
        </Switch>
      </Router>
    )
  }
}

export default App;