package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum ProductStatus {

    ATIVO( "ATIVO" ),
    INATIVO( "INATIVO" );

    private String value;

    public static ProductStatus fromValue( String value ) {
        return Arrays.stream( values() )
                .filter( v -> v.getValue().equalsIgnoreCase( value ) )
                .findFirst()
                .orElse( null );
    }

}
