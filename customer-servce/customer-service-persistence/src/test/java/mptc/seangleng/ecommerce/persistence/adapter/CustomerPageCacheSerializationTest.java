package mptc.seangleng.ecommerce.persistence.adapter;

import mptc.seangleng.ecommerce.common_core.PageResponse;
import mptc.seangleng.ecommerce.domain.customer.dto.CustomerResult;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class CustomerPageCacheSerializationTest {

    @Test
    void customerPageCanBeStoredInRedisCache() throws Exception {
        UUID id = UUID.randomUUID();
        PageResponse<CustomerResult> page = PageResponse.<CustomerResult>builder()
                .items(Collections.unmodifiableList(List.of(
                        new CustomerResult(id, "Leng", "Sean", "sean@example.com", "012345678"))))
                .page(1)
                .size(25)
                .totalPages(1)
                .hasNext(false)
                .build();

        byte[] bytes;
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream();
             ObjectOutputStream output = new ObjectOutputStream(buffer)) {
            output.writeObject(page);
            bytes = buffer.toByteArray();
        }

        try (ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            PageResponse<?> restored = assertInstanceOf(PageResponse.class, input.readObject());
            assertEquals(1, restored.page());
            assertEquals(25, restored.size());
            CustomerResult customer = assertInstanceOf(CustomerResult.class, restored.items().getFirst());
            assertEquals(id, customer.id());
            assertEquals("sean@example.com", customer.email());
        }
    }
}
