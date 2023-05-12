package com.mobile.backend.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.backend.model.order.OrderTrack;
import com.mobile.backend.payload.response.OrderTrackResponse;
import com.mobile.backend.repository.OrderTrackRepository;
import com.mobile.backend.service.IOrderTrackService;

@Service
public class OrderTrackServicImpl implements IOrderTrackService{

	@Autowired
	OrderTrackRepository orderTrackRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public List<OrderTrackResponse> getAllOrderTracks() {
		List<OrderTrack> orderTracks = orderTrackRepository.findAll();
		return Arrays.asList(mapper.map(orderTracks, OrderTrackResponse[].class));
	}

}
