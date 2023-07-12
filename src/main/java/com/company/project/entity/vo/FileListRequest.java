package com.company.project.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileListRequest {

    private Long roleMenuId;

}
