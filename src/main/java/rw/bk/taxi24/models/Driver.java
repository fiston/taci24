package rw.bk.taxi24.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ApiModel(description="All details about the Driver. ")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated employee ID")
    private long id;
    @ApiModelProperty(notes = "The Driver Names")
    private String names;
    @ApiModelProperty(notes = "The Driver's car plates number")
    private String carPlate;
    @ApiModelProperty(notes = "The Driver's license number")
    private String drivingLicenseNumber;
    @ApiModelProperty(notes = "The current longitude location of the driver")
    private double locLong;
    @ApiModelProperty(notes = "The Current latitude location of the driver")
    private double locLat;
    @CreationTimestamp
    @ApiModelProperty(notes = "The Created date-time")
    private Date createdAt;
    @UpdateTimestamp
    @ApiModelProperty(notes = "The updated date-time")
    private Date updatedAt;
}
