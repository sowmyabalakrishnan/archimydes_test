import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavBar';
import { instanceOf } from 'prop-types';
import { Cookies, withCookies } from 'react-cookie';
import './App.css';

class UserStoryEdit extends Component {
  static propTypes = {
    cookies: instanceOf(Cookies).isRequired
  };

  emptyItem = {
    summary: '',
    description: '',
    type: '',
    complexity: '',
    cost: '',
    eta: '',
	status: '',
    createdBy: ''
  };
  
  constructor(props) {
    super(props);
    const {cookies} = props;
    this.state = {
      formItem: this.emptyItem,
      csrfToken: cookies.get('XSRF-TOKEN'),
	  item: (props.location.state?props.location.state.item:undefined),
	  user: (props.location.state?props.location.state.user:undefined)
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
this.handleApprove = this.handleApprove.bind(this);
this.handleReject = this.handleReject.bind(this);
  }

  async componentDidMount() {
	console.log("UserStoryEdit :: componentDidMount :: id, this.props.location.state -- "+this.props.match.params.id+" , "+this.props.location.state);
	if(this.props.location.state)
		{
		this.setState({user: this.props.location.state.user,item: this.props.location.state.item});
		console.log("UserStoryEdit :: componentDidMount :: user -- "+this.props.location.state.user+" , "+this.props.location.state.item);
		}
    if (this.props.match.params.id !== 'new') {
		try {
      	const group = await (await fetch('/api/getStory/'+this.props.match.params.id)).json();
     	this.setState({formItem: group});
 		} catch (error) {
        this.props.history.push('/');
      }
    }
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let formItem = {...this.state.formItem};
    formItem[name] = value;
    this.setState({formItem});
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {formItem} = this.state;
	this.state.formItem.status = "New";
	this.state.formItem.createdBy = this.state.user ? this.state.user.name : "";
    await fetch('/api/story', {method: 'POST', headers: {'Accept': 'application/json','Content-Type': 'application/json'}, body: JSON.stringify(formItem), credentials: 'include'});
	this.props.history.replace({ pathname: '/userstories', state: {user: this.state.user, item: this.state.item} });
    this.props.history.push('/userstories');
  }

async handleApprove(event) {
    event.preventDefault();
	this.state.formItem.status = "Approved";
    const {formItem} = this.state;
    await fetch('/api/story/'+this.props.match.params.id, {method: 'PUT', headers: {'Accept': 'application/json', 'Content-Type': 'application/json'}, body: JSON.stringify(formItem), credentials: 'include'});
console.log("handleapprove in edit page -- "+this.state.item);
	this.props.history.replace({ pathname: '/userstories', state: this.state});
    this.props.history.push('/userstories');
  }

async handleReject(event) {
    event.preventDefault();
	this.state.formItem.status = "Rejected";
    const {formItem} = this.state;
    await fetch('/api/story/'+this.props.match.params.id, {method: 'PUT', headers: {'Accept': 'application/json', 'Content-Type': 'application/json'}, body: JSON.stringify(formItem), credentials: 'include'});
	this.props.history.replace({ pathname: '/userstories', state: {user: this.state.user, item: this.state.item} });
    this.props.history.push('/userstories');
  }


  render() {
    const {formItem} = this.state;
//console.log("formItem.id -- "+formItem.id);
    const title = <h2>{formItem.id ? 'Edit User Story' : 'Add User Story'}</h2>;
 	const disable = formItem.id ? true : false;
//console.log("disable -- "+disable);
//console.log("this.props.location.state.item.roleId -- "+!(this.props.location.state.item.roleId));
	const button = (this.props.match.params.id  && this.props.match.params.id !== 'new' && this.state.formItem.status !== "Approved" && this.state.formItem.status !== "Rejected" && (this.props.location.state.item.roleId)) ?
	<div>
		<Button color="success" type="Approve" onClick={this.handleApprove}>Approve</Button>
		<Button color="danger"  type="Reject" onClick={this.handleReject}>Reject</Button>
	</div>
		: ((this.props.match.params.id  && this.props.match.params.id == 'new') ?//this.state.formItem.status !== "Approved" && this.state.formItem.status !== "Rejected") ?
	<Button color="success" type="Submit">Assign for review</Button>
	:<Button color="primary"><Link to={{pathname: '/userstories', state: {user: this.state.user, item: this.state.item}}}>Back</Link></Button>)
			
    return <div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={this.handleSubmit}>
		<div className="row">
          <FormGroup className="col-md-4 mb-3">
            <Label for="summary">Summary</Label>
            <Input type="text" name="summary" id="summary" value={this.state.formItem.summary} disabled={disable}  onChange={this.handleChange}/>
          </FormGroup>
		  
          <FormGroup className="col-md-5 mb-3">
            <Label for="description">Description</Label>
            <textarea name="description" id="description" value={this.state.formItem.description} disabled={disable} onChange={this.handleChange}/>
          </FormGroup>
		</div>
<div className="row">
          <FormGroup className="col-md-4 mb-3">
            <Label for="type">Type</Label>
            <select type="dropdown" name="type" id="type" value={this.state.formItem.type} disabled={disable} onChange={this.handleChange}> 
			<option></option>
			<option>Enhancement</option>
			<option>Bug Fix</option>
			<option>Dev</option>
			<option>QA</option>
			</select>
		  </FormGroup>
 		<FormGroup className="col-md-5 mb-3">
			<Label for="complexity">Complexity</Label>
            <select type="dropdown" name="complexity" id="complexity" value={this.state.formItem.complexity} disabled={disable}  onChange={this.handleChange}> 
			<option></option>
			<option>Critical</option>
			<option>High</option>
			<option>Medium</option>
			<option>Low</option>
			</select>    
		</FormGroup>
</div>
          <div className="row">
            <FormGroup className="col-md-4 mb-3">
              <Label for="status">Status</Label>
              <Input type="text" name="status" id="status" value={"New"}  disabled="true" onChange={this.handleChange} />
            </FormGroup>
            <FormGroup className="col-md-5 mb-3">
              <Label for="cost">Cost $</Label>
              <Input type="text" pattern="[0-9]*" name="cost" id="cost" value={this.state.formItem.cost} disabled={disable} onChange={this.handleChange} />
            </FormGroup>
            <FormGroup className="col-md-3 mb-3">
              <Label for="eta">ETA</Label>
              <Input type="text" name="eta" id="eta" value={this.state.formItem.eta} disabled={disable} onChange={this.handleChange} />
            </FormGroup>
          </div>
          
		 {button}
          
        </Form>
      </Container>
    </div>
  }
}

export default withCookies(withRouter(UserStoryEdit));