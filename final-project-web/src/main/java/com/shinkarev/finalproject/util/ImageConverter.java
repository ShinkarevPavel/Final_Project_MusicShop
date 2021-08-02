package com.shinkarev.finalproject.util;

import com.shinkarev.finalproject.command.ParamName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ImageConverter {

    public static List<InputStream> convertImage(HttpServletRequest request) throws ServletException, IOException {
        List<InputStream> images = new ArrayList<>();
        for (Part part : request.getParts()) {
            if (part.getName().equals(ParamName.IMAGE_PARAM)) {
                InputStream inputStream = part.getInputStream();
                images.add(inputStream);
            }
        }
        return images;
    }
}