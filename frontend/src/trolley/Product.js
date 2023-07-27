import React, { useState } from "react";

/**
 * A trolley line item
 * @param {*} param0 
 * @returns 
 */
const Product = ( props ) => {

    const [qty, setQty] = useState(props.qty || 0);
    const total = props.price * qty;

    const getPayload = (qty = qty, total = total) => {

        return {
            name: props.name,
            qty: qty,
            price: props.price,
            total: total
        }
    }

    const add = () =>{

        let newQty = qty + 1;
        props.onAdd(getPayload(newQty, newQty * props.price))
        setQty( newQty );
        
    }

    const remove= () =>{
        if( qty === 0) return;

        let newQty = qty - 1;
        props.onRemove(getPayload(newQty, newQty * props.price))
        setQty( newQty );
    }

    return(
        <tr>
            <td>{props.name}</td>
            <td>{props.price}</td>
            <td>
                <button className="btn border" onClick={remove}>-</button>
                &nbsp;&nbsp;{qty}&nbsp;&nbsp;
                <button className="btn border" onClick={add}>+</button>
            </td>
            <td>{total}</td>
        </tr>
    )
}


export default Product;