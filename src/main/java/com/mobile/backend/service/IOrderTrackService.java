package com.mobile.backend.service;

import java.util.List;

import com.mobile.backend.payload.response.OrderTrackResponse;

public interface IOrderTrackService {

	List<OrderTrackResponse> getAllOrderTracks();

}
