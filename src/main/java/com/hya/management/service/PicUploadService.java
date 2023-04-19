package com.hya.management.service;


import com.hya.management.utils.Result;
import org.springframework.web.multipart.MultipartFile;

public interface PicUploadService {
   Result upload(MultipartFile file);
}
