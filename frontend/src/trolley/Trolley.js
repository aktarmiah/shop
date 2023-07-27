import React, { useState, useEffect } from 'react';
import Product from './Product';
import Discount from './Discount';

const Trolley = (props) => {

    const [products, setProducts] = useState({});
    const [data, setData] = useState({});
    const [total, setTotal] = useState(0);

    const onAdd = (payload) =>{
        setData( prevData => ({...prevData, [payload.name]: payload}) );
    }

    const onRemove = (payload) => {
        setData( prevData => ({...prevData, [payload.name]: payload}) );
    }

    useEffect( () => {
        var _total = 0;

        for( let product of Object.values(data) ) {
            _total += product.total;  
        }
        
        if( typeof props.onUpdate === 'function' ) {
            props.onUpdate(data);
        }

        setTotal(_total);

    }, [JSON.stringify(data)]) // React cannot detect changes in objects

    useEffect(() => {
        if(Object.keys(products).length > 0) return;

        let _products = [];
        _products["CD"] = <Product key={"cd"} name={"CD"} price={10.00} qty={0} onAdd={onAdd} onRemove={onRemove} />;
        _products["Book"] = <Product key={"book"} name={"Book"} price={5.00} qty={0} onAdd={onAdd} onRemove={onRemove} />;
        _products["DVD"] = <Product key={"dvd"} name={"DVD"} price={15.00} qty={0} onAdd={onAdd} onRemove={onRemove} />;
        setProducts(_products);
    }, [products]);


    useEffect( () => {

        if( props.discThu == 0 ) return;

        setProducts( prevArr => ({...prevArr, "Thursday": <Discount key={"thu"} name={"Thursday"} discount={props.discThu} />}));
        setTotal( prevTotal => prevTotal-props.discThu );

    }, [props.discThu])


    useEffect( () => {

        if( props.discTFO == 0 ) return;

        setProducts( prevArr => ({...prevArr, "TwoForOne": <Discount key={"tfo"} name={"2 For 1"} discount={props.discThu} />}));
        setTotal( prevTotal => prevTotal-props.discTFO );

    }, [props.discTFO])


	return (
        <div className='container border p-3 col-6 mr-3'>

            <table className='table'>
                <thead>
                    <tr>
                        <th>Product</th>
                        <th>Unit Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                    </tr>                
                </thead>
                <tbody>
                    {Object.values(products)}
                </tbody>            
            </table>
            <div>
                <div className='d-flex border rounded p-3 justify-content-between bg-light'>
                    <h5>Total</h5>
                    <span>{total}</span>
                </div>    
                <div className='d-flex justify-content-end mt-2'>
                    <button onClick={ ()=>alert("Checkout not implemented")} className='btn btn-success'>Checkout</button>
                </div>            
            </div>

        </div>
    );
}

export default Trolley;
