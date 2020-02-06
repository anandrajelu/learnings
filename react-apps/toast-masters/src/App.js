import React from 'react';
import './App.css';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      ranNum: 0,
      oldNums: new Array(0),
      errMsg: ''
    }
    this.handleClick = this.handleClick.bind(this);
  }
  handleClick() {
    let arrLen = this.state.oldNums.length;
    if (arrLen >= 9) {
      this.setState({errMsg: 'Refresh the page to continue..'});
      return null;
    }
    let num = Math.floor((Math.random() * 10)+1);
    while (this.state.oldNums.includes(num)) {
      num = Math.floor((Math.random() * 10)+1);
    }
    let oldNums = this.state.oldNums.slice();
    oldNums.push(num);
    this.setState(state => ({
      ranNum: num,
      oldNums: oldNums
     }));
  }
  render() {
    return (
      <div>
        <h1 className="Dot"> </h1>
        <h1 className="Number">{this.state.ranNum}</h1>
        <button className="Button" onClick={this.handleClick}>Click Me</button>
        <h2>{this.state.errMsg}</h2>
      </div>
    );
  }
}

export default App;
