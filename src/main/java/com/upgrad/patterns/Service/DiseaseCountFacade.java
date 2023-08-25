package com.upgrad.patterns.Service;

import com.upgrad.patterns.Constants.SourceType;
import com.upgrad.patterns.Interfaces.IndianDiseaseStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseaseCountFacade {

    private final IndiaDiseaseStatFactory indiaDiseaseStatFactory;

    @Autowired
    public DiseaseCountFacade(IndiaDiseaseStatFactory indiaDiseaseStatFactory) {
        this.indiaDiseaseStatFactory = indiaDiseaseStatFactory;
    }

    public Object getDiseaseShCount() {
        IndianDiseaseStat diseaseStrategy = indiaDiseaseStatFactory.GetInstance(SourceType.DiseaseSh);
        return diseaseStrategy.GetActiveCount();
    }

    public Object getJohnHopkinCount() {
        IndianDiseaseStat diseaseStrategy = indiaDiseaseStatFactory.GetInstance(SourceType.JohnHopkins);
        return diseaseStrategy.GetActiveCount();
    }

    public Object getInfectedRatio(String sourceType) {
        Float population = 900000000F;
        try {
            SourceType sourceEnum = SourceType.valueOf(sourceType);
            String activeCountStr = indiaDiseaseStatFactory.GetInstance(sourceEnum).GetActiveCount();
            Float active;
            try {
                active = Float.parseFloat(activeCountStr);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid return type. Active count should be a Float");
            }
            Float percent = Float.valueOf((active / population));
            return String.format("%.3f", percent * 100);
        }
        catch (IllegalArgumentException e) {
            String message = String.format("Invalid source type specified. Available source type (%s, %s)", SourceType.DiseaseSh, SourceType.JohnHopkins);
            throw new IllegalArgumentException(message);
        }
        catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred while calculating infected ratio.", e);
        }
    }
    
}
