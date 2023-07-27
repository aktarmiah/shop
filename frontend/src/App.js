import React, { useState } from 'react';
import './App.css';
import Trolley from './trolley/Trolley';

const calculateTwoForOneDiscount = ( product ) => {

	if( product.name !== 'DVD' ) return 0;
	if( product.qty == 0) return 0;

	let pairs = Math.floor(product.qty / 2)
	const discount = pairs * product.price;

	return discount;

}

const calculateThursdayDiscount = ( product ) => {

	const date = new Date();
	const dayName = date.toLocaleDateString(Date.UTC, { weekday: 'long' });     

	if( dayName !== "Thursday" ) return 0;

	const discount = product.total  * 0.2

	return discount;
}

const App = () => {

	const [discount, setDiscount] = useState(0);
	const [discThu, setDiscThu] = useState(0); // Thrusdays
	const [discTFO, setDiscTFO] = useState(0); // 2 for 1
	const [total, setTotal] = useState(0);
	const [qty, setQty] = useState(0);
	
	const onUpdate = ( trolleyData ) => {
		console.log("Received trolley data", trolleyData);

		let _total = 0;
		let _qty = 0;
		let _discount = 0;
		let _discThu = 0;
		let _discTFO = 0;

		for( let product of Object.values(trolleyData) ) {
            _total += product.total;
			_qty += product.qty;
			_discThu += calculateThursdayDiscount( product );
			_discTFO += calculateTwoForOneDiscount( product )
			_discount += _discThu + _discTFO;
        }

		setTotal(_total);
		setQty(_qty);
		setDiscount(_discount);  
		setDiscThu(_discThu);
		setDiscTFO(_discTFO);      
	}
  
	return (
		<div className='container p-3 bg-white mt-3 rounded shadow'>
			<h3 className='text-success'>Welcome to the Generic Retailer</h3>
			<hr />
			<div className='d-flex justify-content-between'>
				<div className='col-6'>
					<div className='card p-3 m-2 mt-0'>
						<h6 className='text-primary'>History</h6>
					</div>
					<div className='card p-3 m-2'>
						<h6 className='text-primary'>Summary</h6>
						<div className='d-flex justify-content-between'>

							<span><strong>Total: </strong>{total}</span>
							<span><strong>Discount: </strong>{discount}</span>
							<span><strong>Quantity: </strong>{qty}</span>
						</div>
					</div>
				</div>
				<Trolley
					onUpdate={onUpdate} 
					discThu={discThu}
					discTFO={discTFO} />
			</div>
			
		</div>
    );
}

export default App;
