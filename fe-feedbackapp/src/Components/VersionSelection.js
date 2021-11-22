import Button from '@restart/ui/esm/Button';
import React from 'react';
import { Carousel, Col, Container, Row, ButtonGroup } from 'react-bootstrap';
import { faAngleLeft, faAngleRight } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faArrowLeft, faArrowRight } from '@fortawesome/free-solid-svg-icons'

function VersionSelection({version, incr, decr}) {
    return (
        <>
            <div >
            <ButtonGroup size="lg" className="mb-2 text-center">
                <FontAwesomeIcon className="me-3 mt-1" icon={faArrowLeft} onClick={decr}/>
                <div className="text-center fw-bold">{version}</div>
                <FontAwesomeIcon className="ms-3 mt-1" icon={faArrowRight} onClick={incr}/>
            </ButtonGroup>
            </div>
        </>
    );
}

export default VersionSelection;