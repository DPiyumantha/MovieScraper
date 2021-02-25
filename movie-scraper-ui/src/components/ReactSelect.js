import React from 'react';

import Select from 'react-select';

export default function ReactSelect(props){ 
    
  return (<Select
    defaultValue={[props.genres[0].name]}
    isMulti={true}
    name="Genres"
    options={props.genres}
    className="basic-multi-select"
    classNamePrefix="select"
  />);

}
