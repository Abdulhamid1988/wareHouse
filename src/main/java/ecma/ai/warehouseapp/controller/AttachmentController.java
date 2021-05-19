package ecma.ai.warehouseapp.controller;

import ecma.ai.warehouseapp.entity.Attachment;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.AttachmentContentRepository;
import ecma.ai.warehouseapp.repository.AttachmentRepository;
import ecma.ai.warehouseapp.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @Autowired
    AttachmentService attachmentService;


    @PostMapping("/upload")
    public ApiResponse upload(MultipartHttpServletRequest request) throws IOException {
        return attachmentService.upload(request);

    }

    @GetMapping("/download/{id}")
    public ApiResponse download(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        return attachmentService.download(id,response);
    }

    @GetMapping("/info")
    public List<Attachment> attachmentList(){
        List<Attachment> attachmentList = attachmentRepository.findAll();
        return attachmentList;
    }

    @GetMapping(value = "/info/{id}")
    public Attachment attachmentGetById(@PathVariable Integer id){
        Optional<Attachment> byId = attachmentRepository.findById(id);
        if (byId.isPresent()){
            Attachment attachment = byId.get();
            return attachment;
        }

        return new Attachment();
    }
}
