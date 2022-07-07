package com.memories.new_life.azure;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.azure.storage.blob.BlobClientBuilder;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobContainer;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AzureAdapter {

	@Autowired
	BlobClientBuilder client;

	@Autowired
	AzureStorageAccount storageAccount;

	@Value("${blob.container-name}")
	String containerName;

	public byte[] getFile(String name) {
		CloudBlobContainer container;
		byte[] data = null;
		try {
			container = storageAccount.getContainerReference(containerName);
			CloudBlob blob = container.getBlockBlobReference(name);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			blob.download(os);
			data = os.toByteArray();
		} catch (Exception e) {
			log.error("Error occured while fetching blob");
		}
		return data;
	}

}
