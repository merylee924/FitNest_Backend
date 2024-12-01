package org.ilisi.service;



import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.locationtech.jts.geom.Point;
import java.io.IOException;

public class PointSerializer extends JsonSerializer<Point> {

    @Override
    public void serialize(Point point, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // Convertir le point en un tableau de [longitude, latitude]
        jsonGenerator.writeStartArray();
        jsonGenerator.writeNumber(point.getX()); // longitude
        jsonGenerator.writeNumber(point.getY()); // latitude
        jsonGenerator.writeEndArray();
    }
}
