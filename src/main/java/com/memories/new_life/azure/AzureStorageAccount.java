package com.memories.new_life.azure;

import java.net.URISyntaxException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;

@Component
public class AzureStorageAccount {

	@Value("${blob.connection-string}")
	String connectionString;

	public synchronized CloudStorageAccount getStorageAccount() {
		CloudStorageAccount storageAccount = null;
		try {
			storageAccount = CloudStorageAccount.parse(connectionString);
		} catch (Exception e) {

		}
		return storageAccount;
	}

	public CloudBlobContainer getContainerReference(String containerName) throws URISyntaxException, StorageException {
		final CloudBlobClient blobClient = getStorageAccount().createCloudBlobClient();
		return blobClient.getContainerReference(containerName.toLowerCase(Locale.getDefault()));
	}
}
