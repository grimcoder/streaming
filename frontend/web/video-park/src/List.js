import React from "react";
import { useEffect, useState } from "react";


export default function FileList(props){
    const list_uri = 'http://192.168.1.32:8080/list';
    const media_uri = 'http://192.168.1.32:3030/';
    const [list, setList] = useState([]);

    function setClip(clip_uri){
        props.onSelect(clip_uri);
    }

    useEffect(()=>{
        fetch(list_uri)
        .then(stream=>stream.json())
        .then(data=>setList(data));

    }, [list]);

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