import React from 'react';
import ReactDOM from 'react-dom';
import './App.css';
class Timer extends React.Component {
  render() {
    return (
      <div>{this.props.value}</div>
      );
  }
  };

  function displayTimer() {
    ReactDOM.render(<h1>{new Date().toLocaleTimeString()}</h1>, document.getElementById('root'));
  }

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      value: new Date().toLocaleTimeString()
    }
  }
  render() {
    return (
    <div>
      <Timer value = {this.state.value}/>
    </div>
    );
  }
}
setInterval(displayTimer, 1000);
export default App;
