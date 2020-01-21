import React, { Component } from 'react';
import { Link } from 'react-router-dom';
class Details extends Component {

    render() {
        let news = '';

        if(this.props.location.state.key){

              return (
                  <div>
                          <div className="title">{this.props.location.state.key.title}</div>
                         {/*  <div className="row">this.props.location.key.[score]</div>
                          <div className="row">this.props.location.key.[by]</div>
                          <div className="row">this.props.location.key.[karma]</div>
                          <div className="row">this.props.location.key.[created]</div>  */}
                  </div>
                  )

        }

        return(

          <div className="mainStart">
                <h1> This is a details page </h1>
                {news}
                <Link to='/'>Home</Link>
          </div>
        )
      }
}

export default Details;