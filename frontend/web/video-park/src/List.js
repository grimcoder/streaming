import React from "react";
import { useEffect, useState } from "react";


export default function FileList(props){
    const list_uri = process.env.REACT_APP_LIST_URI;

    const [list, setList] = useState([]);

    function setClip(clip_uri){
        props.onSelect(clip_uri);
    }

    useEffect(()=>{
        fetch(list_uri)
        .then(stream=>stream.json())
        .then(data=>setList(data));

    }, []);

    return (
        <div>
            {list && list.map((item, i)=>{
                return (
                    <div onClick={()=>{
                        setClip(item.uri)
                    }}>
                        {item.name}
                    </div>
                );
            })}
        </div>
    );
}