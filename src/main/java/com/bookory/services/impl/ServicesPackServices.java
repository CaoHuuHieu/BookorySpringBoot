package com.bookory.services.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookory.EntityAndDto.ServicePackConvert;
import com.bookory.dto.response.ServicePackResponseDTO;
import com.bookory.entity.ServicePack;
import com.bookory.repository.ServicePackRepository;


@Service
public class ServicesPackServices {
	@Autowired
 	ImageStorageService storageServices;
	@Autowired
	ServicePackRepository serviceRepository;
	@Autowired
	ServicePackConvert servicePackConvert;
	public List<ServicePackResponseDTO> getAllServices() throws IOException{
		List<ServicePack> servicePackEntities = serviceRepository.findAll();
		if(servicePackEntities.size() > 0) {
			return servicePackConvert.toServicePackResponseDTO(servicePackEntities);
		}else
			return null;
	}

	public ServicePackResponseDTO getServicesByID(long id) throws IOException{
		ServicePack servicePackEntity =  serviceRepository.findById(id).orElse(null);
		if(servicePackEntity != null) {
			return servicePackConvert.toServicePackResponseDTO(servicePackEntity);
		}else 
			return null;
	}

	public ServicePack addNewService(ServicePack service, MultipartFile file) {
		if(file != null)
			service.setThumbnail(storageServices.storeFile(file));
		service.setStatus(0);
		return serviceRepository.save(service);
	}

	public ServicePack updateService(long id, ServicePack service, MultipartFile file) {
		if(file != null) {
			service.setThumbnail(storageServices.storeFile(file));
		}
		service.setId(id);
		return serviceRepository.save(service);
	}

	public ServicePack deleteService(long id){
		ServicePack serviceEntity = serviceRepository.findById(id).get();
		serviceEntity.setStatus(1);
		return serviceRepository.save(serviceEntity);
	}
}
