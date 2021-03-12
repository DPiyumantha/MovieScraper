import React, { Component } from 'react'
import Typical from 'react-typical'

class TextTyping extends React.Component {
  render () {
    return (
      <Typical
        steps={['Movie scraper is waiting... 🤗', 2000,'Hmm... did you refresh? 🤔', 1000, "Let's wait for few more minutes! 😒", 1000]}
        loop={Infinity}
        wrapper="p"
      />
    )
  }
}
export default TextTyping;