import React, { useState } from "react";

/**
 * A trolley line item
 * @param {*} param0 
 * @returns 
 */
const Discount = ( props ) => {

    return(
        <tr>
            <td colSpan={3}>{props.name}</td>
            <td>-{props.discount}</td>
        </tr>
    )
}


export default Discount;