package tn.esprit.pidev.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {
    private String _id;
    private String response;
}
