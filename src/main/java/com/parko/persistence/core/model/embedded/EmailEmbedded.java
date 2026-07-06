package com.parko.persistence.core.model.embedded;

import com.parko.domain.lib.model.InstitutionalDomain;

public record EmailEmbedded(
        String value,
        String studentId,
        InstitutionalDomain institutionalDomain
) {
}
