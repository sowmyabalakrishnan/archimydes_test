import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavBar';
import Home from './UserStoryList';

class UserStoryEdit extends Component {

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
   isHidden: false
  

  constructor(props) {
    super(props);
    this.state = {
      item: this.emptyItem
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
	  console.log(this.props.match.params.id);
	  console.log(this.props.match.params.status);
	  console.log(this.props.match.params.summary);
	  console.log(this.props.match.params.description);
	  console.log(this.props.match.params.cost);
	  console.log(this.props.match.params.eta);
	  console.log(this.props.match.params.createdBy);
	  
    if (this.props.match.params.id !== 'new') {
      const group = await (await fetch('/api/getStory/'+this.props.match.params.id)).json();
      this.setState({item: group});
    }
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = {...this.state.item};
    item[name] = value;
    this.setState({item});
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {item} = this.state;

    await fetch('/api/group', {
      method: (item.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(item),
    });
    this.props.history.push('/groups');
  }

  render() {
    const {item} = this.state;
    const title = <h2>{item.id ? 'Edit Group' : 'Add Group'}</h2>;

    return <div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label for="summary">Summary</Label>
            <Input type="text" name="summary" id="summary" value={item.id ? item.summary : ''} disabled="true" onChange={this.handleChange}/>
          </FormGroup>
		  
          <FormGroup>
            <Label for="description">Description</Label>
            <textarea name="description" id="description" value={item.id ? item.description : ''} disabled="true" onChange={this.handleChange}/>
          </FormGroup>
          <FormGroup>
            <Label for="type">Type</Label>
            <select type="dropdown" name="type" id="type" value={item.id ? item.type : ''} disabled="true" onChange={this.handleChange} />
		  </FormGroup>
          <div className="row">
            <FormGroup className="col-md-4 mb-3">
              <Label for="status">Status</Label>
              <Input type="text" name="status" id="status" value={item.id ? item.status : ''} onChange={this.handleChange} disabled="true"/>
            </FormGroup>
            <FormGroup className="col-md-5 mb-3">
              <Label for="cost">Cost</Label>
              <Input type="text" name="cost" id="cost" value={item.id ?'$'+item.cost : ''} onChange={this.handleChange} disabled="true"/>
            </FormGroup>
            <FormGroup className="col-md-3 mb-3">
              <Label for="eta">ETA</Label>
              <Input type="text" name="eta" id="eta" value={item.id ? item.eta : ''} onChange={this.handleChange} disabled="true"/>
            </FormGroup>
          </div>
          <FormGroup>
		  
            <Button color="success" type="Submit">Approve</Button>{' '}
			<Button color="danger">Reject</Button>{' '}
			<div className={this.props.match.params.id ? 'hidden' : ''}>
            <Button color="success" type="Submit">Create</Button>{' '}
			</div>
          </FormGroup>
        </Form>
      </Container>
    </div>
  }
}

export default withRouter(UserStoryEdit);