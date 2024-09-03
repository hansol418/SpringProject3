package com.busanit501.springproject3.lcs.Repository;

import com.busanit501.springproject3.lcs.Dto.ImageDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<ImageDocument, String> {
}

