import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.css';

class App extends Component {
    render() {
        return (
            <div>
                <nav class="navbar navbar-light bg-light">Expression Evaluator</nav>
                <div>
                    <Keys />
                </div>
            </div>
        );
    }
}

class Key extends Component {
    render() {
        return (<button className='btn btn-light' onClick={this.props.onClick}>{this.props.keyVal}</button>);
    }
}

class Keys extends Component {
    constructor(props) {
        super(props);
        this.state = {
            reset: true,
            outputVal: ''
        }
    }
    renderKey(val) {
        return <Key keyVal={val} onClick={() => this.updateOutputVal(val)} />;
    }
    updateOutputVal(outputVal) {
        switch (outputVal) {
            case 'AC': {
                this.setState({ outputVal: '' });
                break;
            }
            case '=': {
                const result = this.calculateResult();
                this.setState({ outputVal: result, reset: true });
                break;
            }
            default: {
                if (this.state.reset) {
                    this.state.outputVal = '';
                }
                let currVal = this.state.outputVal;
                currVal += outputVal;
                this.setState({ outputVal: currVal, reset: false });
            }
        }
    }
    calculateResult() {
        let result = 0;
        try {
            result = eval(this.state.outputVal);
        } catch (error) {
            console.log(error);
        }
        return result;
    }
    render() {
        return (
            <div align="center">
                <textarea className='input-group-text' width='100px'
                    height='100px' vertical-align='middle' readOnly value={this.state.outputVal}></textarea>
                <div className="key-row">
                    {this.renderKey('AC')}
                    {this.renderKey(' ')}
                    {this.renderKey(' ')}
                    {this.renderKey('=')}
                </div>
                <div className="key-row">
                    {this.renderKey('1')}
                    {this.renderKey('2')}
                    {this.renderKey('3')}
                    {this.renderKey('+')}
                </div>
                <div className="key-row">
                    {this.renderKey('4')}
                    {this.renderKey('5')}
                    {this.renderKey('6')}
                    {this.renderKey('-')}
                </div>
                <div className="key-row">
                    {this.renderKey('7')}
                    {this.renderKey('8')}
                    {this.renderKey('9')}
                    {this.renderKey('*')}
                </div>
                <div className="key-row">
                    {this.renderKey(' ')}
                    {this.renderKey('0')}
                    {this.renderKey(' ')}
                    {this.renderKey('/')}
                </div>
            </div>
        );
    }
}

export default App;